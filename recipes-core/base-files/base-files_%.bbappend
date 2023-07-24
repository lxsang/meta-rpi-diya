hostname = "diya"

do_install:append () {
    cat << EOF >> "${D}${sysconfdir}/profile"
# QT related setting
export QT_QPA_PLATFORM=wayland-egl
export XDG_RUNTIME_DIR=/run/user/1000
# export QT_QPA_EGLFS_ROTATION=90
EOF
}