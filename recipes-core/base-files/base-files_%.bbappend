hostname = "diya"
do_install:append () {
    cat >> ${D}${sysconfdir}/fstab <<EOF
# add bind mount for /etc
/etc /var/etc none defaults,bind 0 0
EOF
}