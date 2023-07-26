do_install:append() {
    # grant all permission to sudo group
    sed -i 's/^#\s*\(%sudo\s*ALL=(ALL:ALL)\s*ALL\)/\1/'  ${D}/${sysconfdir}/sudoers
    # allow sudo group to power off/reboot system without password
    cat << EOF >> ${D}/${sysconfdir}/sudoers
## sudo user group is allowed to execute halt and reboot 
%sudo ALL=NOPASSWD: /sbin/halt, /sbin/reboot, /sbin/poweroff, /usr/bin/swaylock
EOF
}