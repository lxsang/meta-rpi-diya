
KERNEL_DEVICETREE:append = "${@bb.utils.contains('RPI_USE_wS_28_DPI_LCD', '1', ' overlays/dpi24.dtbo overlays/vc4-kms-dpi-generic.dtbo overlays/ads1115-i2c-gpio.dtbo overlays/ads1115-i2c-gpio.dtbo overlays/vc4-kms-DPI-28inch.dtbo overlays/waveshare-28dpi-3b-4b.dtbo overlays/waveshare-28dpi-3b.dtbo overlays/waveshare-28dpi-4b.dtbo ', ' ', d)}"                        "
IMAGE_BOOT_FILES:append = " extraconfig.txt "

IMAGE_INSTALL:append = " waveshare-2.8-lcd realtekfw "