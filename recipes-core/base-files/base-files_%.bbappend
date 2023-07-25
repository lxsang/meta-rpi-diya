hostname = "diya"

do_install:append () {
    cat << EOF >> "${D}${sysconfdir}/profile"
# QT related setting
export QT_QPA_PLATFORM=wayland-egl
export XDG_RUNTIME_DIR=/home/$USER/.xdg
export PATH=$PATH:/home/$USER/bin
if [ ! -e "$XDG_RUNTIME_DIR" ]; then
    mkdir -p "$XDG_RUNTIME_DIR"
fi
EOF
}