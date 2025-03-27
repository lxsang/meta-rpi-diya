hostname = "diya"

do_install:append () {
    cat << 'EOF' >> "${D}${sysconfdir}/profile"
export DISK="mmcblk0"
EOF
}
