inherit extrausers

KERNEL_DEVICETREE:append = "${@bb.utils.contains('RPI_USE_wS_28_DPI_LCD', '1', ' overlays/waveshare-28dpi-3b-4b.dtbo overlays/waveshare-28dpi-3b.dtbo overlays/waveshare-28dpi-4b.dtbo ', ' ', d)}"


EXTRA_USERS_PARAMS = "usermod -p BJpK8ADNDLsGg root; \
                        usermod -p 2Pe/4xyFxsokE diya; \
                        usermod -a -G video diya; \
                        usermod -a -G tty diya; \
                        usermod -a -G input diya; \
                        usermod -a -G dialout diya; \
                        usermod -a -G audio diya; \
                        usermod -a -G avahi diya \
                        "
IMAGE_BOOT_FILES:append = "fs_resize"
# KERNEL_DEVICETREE:append = "${@bb.utils.contains('RPI_USE_wS_28_DPI_LCD', '1', ' overlays/vc4-kms-dpi-generic.dtbo overlays/waveshare-28dpi-3b-4b.dtbo overlays/waveshare-28dpi-3b.dtbo overlays/waveshare-28dpi-4b.dtbo ', ' ', d)}"

IMAGE_INSTALL:append = "mpg123 \
                        libcurl \
                        libdrm \
                        libgbm \
                        libgles2 \
                        libegl-mesa \
                        libgl-mesa \
                        libglapi \
                        glew \
                        freeglut \
                        mesa \
                        libsdl2 \
                        libsdl2-ttf \
                        libsdl2-image \
                        libsdl2-mixer \
                        libsdl2-net \
                        example \
                        htop \
                        waveshare-2.8-lcd \
                        realtekfw \
                        diya-overlay \
                        opensmalltalk-cog-vm \
                        " 