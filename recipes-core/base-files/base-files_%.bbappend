hostname = "diya"

do_install:append () {
    cat << 'EOF' >> "${D}${sysconfdir}/profile"
export GDK_BACKEND=wayland
export XDG_RUNTIME_DIR=/home/$USER/.xdg
export XDG_CONFIG_HOME=/home/$USER/.config
export PATH=$PATH:/home/$USER/bin:/home/$USER/.local/bin
export LD_LIBRARY_PATH=/home/$USER/lib:/home/$USER/.local/lib
if [ ! -e "$XDG_RUNTIME_DIR" ]; then
    mkdir -p "$XDG_RUNTIME_DIR"
fi
export TERM=xterm-256color
EOF

cat << EOF >> "${D}${sysconfdir}/profile"
export MACHINE=$MACHINE
EOF
}
