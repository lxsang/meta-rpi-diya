// To compile with gcc:  (tested on Ubuntu 14.04 64bit):
//	 g++ sdl2_opengl.cpp -lSDL2 -lGL
// To compile with msvc: (tested on Windows 7 64bit)
//   cl sdl2_opengl.cpp /I C:\sdl2path\include /link C:\path\SDL2.lib C:\path\SDL2main.lib /SUBSYSTEM:CONSOLE /NODEFAULTLIB:libcmtd.lib opengl32.lib

#include <stdio.h>
#include <stdint.h>
#include <assert.h>
#include <SDL2/SDL.h>
#include <SDL2/SDL_opengl.h>
#include <GLES/gl.h>

typedef int32_t i32;
typedef uint32_t u32;
typedef int32_t b32;

#define WinWidth 480
#define WinHeight 640
#define VERTEX_SHADER "\
void main() \
{ \
	gl_Position = gl_ModelViewProjectionMatrix * gl_Vertex; \
} \
"

#define FRAG_SHADER "\
void main() \
{ \
	gl_FragColor = vec4(1.0,0.0,0.0,1.0); \
} \
"

GLuint load_shader(c)
{

    // Create the shaders
    GLuint VertexShaderID = glCreateShader(GL_VERTEX_SHADER);
    GLuint FragmentShaderID = glCreateShader(GL_FRAGMENT_SHADER);

    GLint Result = GL_FALSE;
    int InfoLogLength;
    char *source;
    char buff[2048];
    // Compile Vertex Shader
    source = VERTEX_SHADER;
    printf("Compiling vertex shader \n");
    glShaderSource(VertexShaderID, 1, &source, NULL);
    glCompileShader(VertexShaderID);

    memset(buff, 0, sizeof(buff));
    // Check Vertex Shader
    glGetShaderiv(VertexShaderID, GL_COMPILE_STATUS, &Result);
    glGetShaderiv(VertexShaderID, GL_INFO_LOG_LENGTH, &InfoLogLength);
    if (InfoLogLength > 0)
    {
        glGetShaderInfoLog(VertexShaderID, InfoLogLength, NULL, &buff);
        printf("%s\n", buff);
    }

    // Compile Fragment Shader
    source = FRAG_SHADER;
    printf("Compiling frag shader \n");
    glShaderSource(FragmentShaderID, 1, &source, NULL);
    glCompileShader(FragmentShaderID);

    // Check Fragment Shader
    memset(buff, 0, sizeof(buff));
    glGetShaderiv(FragmentShaderID, GL_COMPILE_STATUS, &Result);
    glGetShaderiv(FragmentShaderID, GL_INFO_LOG_LENGTH, &InfoLogLength);
    if (InfoLogLength > 0)
    {
        glGetShaderInfoLog(FragmentShaderID, InfoLogLength, NULL, buff);
        printf("%s\n", buff);
    }

    // Link the program
    printf("Linking program\n");
    GLuint ProgramID = glCreateProgram();
    glAttachShader(ProgramID, VertexShaderID);
    glAttachShader(ProgramID, FragmentShaderID);
    glLinkProgram(ProgramID);

    // Check the program
    memset(buff, 0, sizeof(buff));
    glGetProgramiv(ProgramID, GL_LINK_STATUS, &Result);
    glGetProgramiv(ProgramID, GL_INFO_LOG_LENGTH, &InfoLogLength);
    if (InfoLogLength > 0)
    {
        glGetProgramInfoLog(ProgramID, InfoLogLength, NULL, buff);
        printf("Linking: %s\n", buff);
    }

    glDetachShader(ProgramID, VertexShaderID);
    glDetachShader(ProgramID, FragmentShaderID);

    glDeleteShader(VertexShaderID);
    glDeleteShader(FragmentShaderID);

    return ProgramID;
}

int main(int ArgCount, char **Args)
{

    u32 WindowFlags = SDL_WINDOW_OPENGL;
    
    SDL_Window *Window = SDL_CreateWindow("OpenGL Test", 0, 0, 0, 0, WindowFlags);
    assert(Window);
    SDL_GLContext Context = SDL_GL_CreateContext(Window);
    SDL_SetWindowFullscreen(Window, SDL_WINDOW_FULLSCREEN);
    SDL_DisplayMode DM;
    SDL_GetCurrentDisplayMode(0, &DM);
    printf("Display size %dx%d\n", DM.w, DM.h);
    int Running = 1;

    // openGL part
    GLuint VertexArrayID;
    glGenVertexArrays(1, &VertexArrayID);
    glBindVertexArray(VertexArrayID);
    printf("Vertex Array ID %d\n", VertexArrayID);
    // Create and compile our GLSL program from the shaders
    GLuint programID = load_shader();
    GLfloat tmp_buffer[9]; 
    static const GLfloat g_vertex_buffer_data[] = {
        -1.0f,
        -1.0f,
        0.0f,
        1.0f,
        -1.0f,
        0.0f,
        0.0f,
        1.0f,
        0.0f,
    };

    GLuint vertexbuffer;
    GLint currid = -1;
    glGenBuffers(1, &vertexbuffer);
    glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
    glBufferData(GL_ARRAY_BUFFER, sizeof(g_vertex_buffer_data), g_vertex_buffer_data, GL_STATIC_DRAW);
    printf("Buffer ID %d of size: %d\n", vertexbuffer, sizeof(tmp_buffer));
    // read back buffer data
    memset(tmp_buffer, 0, sizeof(tmp_buffer));
    glGetBufferSubData(GL_ARRAY_BUFFER, 0,sizeof(tmp_buffer), tmp_buffer );
    printf("Read back buffer data: \n");

    for(int i=0; i< 9; i++)
    {
        printf("%d: %.2f\n", i, tmp_buffer[i]);
    }
    glGetIntegerv(GL_ARRAY_BUFFER_BINDING, &currid);
    printf("current id: %d\n",currid);
    while (Running)
    {
        SDL_Event Event;
        while (SDL_PollEvent(&Event))
        {
            if (Event.type == SDL_KEYDOWN)
            {
                switch (Event.key.keysym.sym)
                {
                case SDLK_ESCAPE:
                    Running = 0;
                    break;
                default:
                    break;
                }
            }
            else if (Event.type == SDL_QUIT)
            {
                Running = 0;
            }
        }

        glViewport(0, 0, DM.w, DM.h);
        glClearColor(1.f, 0.f, 1.f, 0.f);
        glClear(GL_COLOR_BUFFER_BIT);

        
        // Use our shader
        glUseProgram(programID);

        // 1rst attribute buffer : vertices
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vertexbuffer);
        glVertexAttribPointer(
            0,        // attribute 0. No particular reason for 0, but must match the layout in the shader.
            3,        // size
            GL_FLOAT, // type
            GL_FALSE, // normalized?
            0,        // stride
            (void *)0 // array buffer offset
        );

        // Draw the triangle !
        glDrawArrays(GL_TRIANGLES, 0, 3); // 3 indices starting at 0 -> 1 triangle

        glDisableVertexAttribArray(0);
        /* Send our triangle data to the pipeline. */
        SDL_GL_SwapWindow(Window);
    }


    // Cleanup VBO
	glDeleteBuffers(1, &vertexbuffer);
	glDeleteVertexArrays(1, &VertexArrayID);
	glDeleteProgram(programID);
    return 0;
}