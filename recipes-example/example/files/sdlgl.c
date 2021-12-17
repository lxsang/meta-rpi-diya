// To compile with gcc:  (tested on Ubuntu 14.04 64bit):
//	 g++ sdl2_opengl.cpp -lSDL2 -lGL
// To compile with msvc: (tested on Windows 7 64bit)
//   cl sdl2_opengl.cpp /I C:\sdl2path\include /link C:\path\SDL2.lib C:\path\SDL2main.lib /SUBSYSTEM:CONSOLE /NODEFAULTLIB:libcmtd.lib opengl32.lib

#include <stdio.h>
#include <stdint.h>
#include <assert.h>
#include <SDL2/SDL.h>
#include <SDL2/SDL_opengl.h>
#include <GL/gl.h>

typedef int32_t i32;
typedef uint32_t u32;
typedef int32_t b32;

#define WinWidth 480
#define WinHeight 640

int main(int ArgCount, char **Args)
{

    u32 WindowFlags = SDL_WINDOW_OPENGL;
    SDL_Window *Window = SDL_CreateWindow("OpenGL Test", 0, 0, 0, 0, WindowFlags);
    assert(Window);
    SDL_GLContext Context = SDL_GL_CreateContext(Window);

    static GLfloat v0[] = {-1.0f, -1.0f, 1.0f};
    static GLfloat v1[] = {1.0f, -1.0f, 1.0f};
    static GLfloat v2[] = {1.0f, 1.0f, 1.0f};
    static GLfloat v3[] = {-1.0f, 1.0f, 1.0f};
    static GLfloat v4[] = {-1.0f, -1.0f, -1.0f};
    static GLfloat v5[] = {1.0f, -1.0f, -1.0f};
    static GLfloat v6[] = {1.0f, 1.0f, -1.0f};
    static GLfloat v7[] = {-1.0f, 1.0f, -1.0f};
    static GLubyte red[] = {255, 0, 0, 255};
    static GLubyte green[] = {0, 255, 0, 255};
    static GLubyte blue[] = {0, 0, 255, 255};
    static GLubyte white[] = {255, 255, 255, 255};
    static GLubyte yellow[] = {0, 255, 255, 255};
    static GLubyte black[] = {0, 0, 0, 255};
    static GLubyte orange[] = {255, 255, 0, 255};
    static GLubyte purple[] = {255, 0, 255, 0};
    SDL_SetWindowFullscreen(Window,SDL_WINDOW_FULLSCREEN);
    SDL_DisplayMode DM;
    SDL_GetCurrentDisplayMode(0, &DM);
    printf("Display size %dx%d\n", DM.w, DM.h);
    int Running = 1;
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
        /* Send our triangle data to the pipeline. */
        glBegin(GL_TRIANGLES);

        glColor4ubv(red);
        glVertex3fv(v0);
        glColor4ubv(green);
        glVertex3fv(v1);
        glColor4ubv(blue);
        glVertex3fv(v2);

        glColor4ubv(red);
        glVertex3fv(v0);
        glColor4ubv(blue);
        glVertex3fv(v2);
        glColor4ubv(white);
        glVertex3fv(v3);

        glColor4ubv(green);
        glVertex3fv(v1);
        glColor4ubv(black);
        glVertex3fv(v5);
        glColor4ubv(orange);
        glVertex3fv(v6);

        glColor4ubv(green);
        glVertex3fv(v1);
        glColor4ubv(orange);
        glVertex3fv(v6);
        glColor4ubv(blue);
        glVertex3fv(v2);

        glColor4ubv(black);
        glVertex3fv(v5);
        glColor4ubv(yellow);
        glVertex3fv(v4);
        glColor4ubv(purple);
        glVertex3fv(v7);

        glColor4ubv(black);
        glVertex3fv(v5);
        glColor4ubv(purple);
        glVertex3fv(v7);
        glColor4ubv(orange);
        glVertex3fv(v6);

        glColor4ubv(yellow);
        glVertex3fv(v4);
        glColor4ubv(red);
        glVertex3fv(v0);
        glColor4ubv(white);
        glVertex3fv(v3);

        glColor4ubv(yellow);
        glVertex3fv(v4);
        glColor4ubv(white);
        glVertex3fv(v3);
        glColor4ubv(purple);
        glVertex3fv(v7);

        glColor4ubv(white);
        glVertex3fv(v3);
        glColor4ubv(blue);
        glVertex3fv(v2);
        glColor4ubv(orange);
        glVertex3fv(v6);

        glColor4ubv(white);
        glVertex3fv(v3);
        glColor4ubv(orange);
        glVertex3fv(v6);
        glColor4ubv(purple);
        glVertex3fv(v7);

        glColor4ubv(green);
        glVertex3fv(v1);
        glColor4ubv(red);
        glVertex3fv(v0);
        glColor4ubv(yellow);
        glVertex3fv(v4);

        glColor4ubv(green);
        glVertex3fv(v1);
        glColor4ubv(yellow);
        glVertex3fv(v4);
        glColor4ubv(black);
        glVertex3fv(v5);

        glEnd();

        SDL_GL_SwapWindow(Window);
    }
    return 0;
}