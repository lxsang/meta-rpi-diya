DEPENS += " mesa libdrm virtual/libgbm alsa virtual/libgles2 "
EXTRA_OECONF:append=" --disable-video-x11 \
                        --disable-video-wayland \
                        --enable-video-kmsdrm \
                        --enable-video-opengles"