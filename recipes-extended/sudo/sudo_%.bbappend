do_install:append() {
    # grant all permission to sudo group
    sed -i 's/^#\s*\(%sudo\s*ALL=(ALL:ALL)\s*ALL\)/\1/'  ${D}/${sysconfdir}/sudoers
}