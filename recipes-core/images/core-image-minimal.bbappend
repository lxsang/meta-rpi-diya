inherit extrausers
inherit populate_sdk_qt5

KERNEL_DEVICETREE:append = "${@bb.utils.contains('RPI_USE_wS_28_DPI_LCD', '1', ' overlays/dpi24.dtbo overlays/vc4-kms-dpi-generic.dtbo overlays/ads1115-i2c-gpio.dtbo overlays/ads1115-i2c-gpio.dtbo overlays/vc4-kms-DPI-28inch.dtbo overlays/waveshare-28dpi-3b-4b.dtbo overlays/waveshare-28dpi-3b.dtbo overlays/waveshare-28dpi-4b.dtbo ', ' ', d)}"

# usermod -p BJpK8ADNDLsGg root;
EXTRA_USERS_PARAMS = "usermod -p 2Pe/4xyFxsokE diya; \
                        usermod -a -G video diya; \
                        usermod -a -G tty diya; \
                        usermod -a -G input diya; \
                        usermod -a -G dialout diya; \
                        usermod -a -G audio diya; \
                        usermod -a -G avahi diya; \
                        usermod -a -G sudo diya \
                        "
IMAGE_BOOT_FILES:append = "fs_resize"

IMAGE_INSTALL:append = "libcurl \
                        libdrm \
                        libgbm \
                        libgles2 \
                        libgles3 \
                        libegl-mesa \
                        libglapi \
                        libudev \
                        udev \
                        glew \
                        freetype \
                        mesa \
                        sudo \
                        libsdl2 \
                        libsdl2-ttf \
                        libsdl2-image \
                        libsdl2-mixer \
                        libsdl2-net \
                        htop \
                        waveshare-2.8-lcd \
                        realtekfw \
                        diya-overlay \
                        i2c-tools \
                        bash \
                        nano \
                        wpa-supplicant \
                        evtest \
                        raspi-gpio \
                        qtbase \
                        qt5-opengles2-test \
                        " 

