hostname = "diya"
do_install:append () {
    cat >> ${D}${sysconfdir}/fstab <<EOF
# add overlay for /etc
overlay /test overlay defaults,nofail,lowerdir=/etc,upperdir=/var/etc/upper,workdir=/var/etc/work 0 2
EOF
}