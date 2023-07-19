hostname = "diya"

do_install:append () {
    cat << EOF >> "${D}${sysconfdir}/profile"
# QT related setting
export QT_QPA_PLATFORM=eglfs
export QT_QPA_EGLFS_ROTATION=90
EOF
}