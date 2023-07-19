DEPENS += " mesa libdrm alsa virtual/libgbm virtual/libgles2 virtual/libgl udev libudev "

PACKAGECONFIG = " kmsdrm opengl gles2 alsa libusb "
# PACKAGECONFIG:remove = "x11 wayland"