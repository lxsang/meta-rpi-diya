#include <SDL2/SDL.h>
#include <iostream>
#include <vector>
#include <cstdlib>

int main( int argc, char** argv )
{
    SDL_Init( 0 );

    std::cout << "Testing video drivers..." << '\n';
    std::vector< bool > drivers( SDL_GetNumVideoDrivers() );
    for( int i = 0; i < drivers.size(); ++i )
    {
        drivers[ i ] = ( 0 == SDL_VideoInit( SDL_GetVideoDriver( i ) ) );
        SDL_VideoQuit();
    }

    std::cout << "SDL_VIDEODRIVER available:";
    for( int i = 0; i < drivers.size(); ++i )
    {
        std::cout << " " << SDL_GetVideoDriver( i );
    }
    std::cout << '\n';

    std::cout << "SDL_VIDEODRIVER usable   :";
    for( int i = 0; i < drivers.size(); ++i )
    {
        if( !drivers[ i ] ) continue;
        std::cout << " " << SDL_GetVideoDriver( i );
    }
    std::cout << '\n';

    if( SDL_Init( SDL_INIT_EVERYTHING ) < 0 )
    {
        std::cerr << "SDL_Init(): " << SDL_GetError() << '\n';
        return EXIT_FAILURE;
    }
    std::cout << "SDL_VIDEODRIVER selected : " << SDL_GetCurrentVideoDriver() << '\n';

    SDL_Window* window = SDL_CreateWindow
        (
        "SDL2",
        SDL_WINDOWPOS_UNDEFINED, SDL_WINDOWPOS_UNDEFINED,
        0,0,
        SDL_WINDOW_SHOWN
        );
    if( nullptr == window )
    {
        std::cerr << "SDL_CreateWindow(): " << SDL_GetError() << '\n';
        return EXIT_FAILURE;
    }

    std::cout << "SDL_RENDER_DRIVER available:";
    for( int i = 0; i < SDL_GetNumRenderDrivers(); ++i )
    {
        SDL_RendererInfo info;
        SDL_GetRenderDriverInfo( i, &info );
        std::cout << " " << info.name;
    }
    std::cout << '\n';
    SDL_SetWindowFullscreen(window,SDL_WINDOW_FULLSCREEN);
    SDL_Renderer* renderer = SDL_CreateRenderer( window, -1, SDL_RENDERER_ACCELERATED );
    if( nullptr == renderer )
    {
        std::cerr << "SDL_CreateRenderer(): " << SDL_GetError() << '\n';
        return EXIT_FAILURE;
    }
    SDL_RendererInfo info;
    SDL_GetRendererInfo( renderer, &info );
    std::cout << "SDL_RENDER_DRIVER selected : " << info.name << '\n';
    SDL_DisplayMode DM;
    SDL_GetCurrentDisplayMode(0, &DM);
    printf("Display size %dx%d\n", DM.w, DM.h);
    bool running = true;
    while( running )
    {
        SDL_Event ev;
        while( SDL_PollEvent( &ev ) )
        {
            switch (ev.type)
            {
            case SDL_QUIT:
                /* code */
                running = false;
                break;
            case SDL_KEYDOWN:
                //if(ev.key.keysym.sym == SDLK_ESCAPE)
                //{
                running = false;
                //}
                break;
            case SDL_FINGERDOWN:
            case SDL_FINGERMOTION:
                std::cout << "Mouse finger down: x = " << ev.tfinger.x << ", y = " << ev.tfinger.y << std::endl;
                SDL_WarpMouseInWindow(window, (int)(ev.tfinger.x*DM.w), (int)(ev.tfinger.y*DM.h));
                break;
            default:
                break;
            }
        }

        SDL_SetRenderDrawColor( renderer, rand() % 256, rand() % 256, rand() % 256, SDL_ALPHA_OPAQUE );
        SDL_RenderClear( renderer );
        SDL_RenderPresent( renderer );
        SDL_Delay(50);
    }

    SDL_DestroyRenderer( renderer );
    SDL_DestroyWindow( window );
    SDL_Quit();
    return 0;
}