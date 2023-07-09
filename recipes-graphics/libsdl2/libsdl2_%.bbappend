DEPENS += " mesa libdrm virtual/libgbm virtual/libgles2 "
# alsa
EXTRA_OECONF:append=" --disable-video-x11 \
                        --disable-video-wayland \
                        --enable-video-kmsdrm \
                        --enable-video-opengles"