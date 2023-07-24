
# change default weston user to diya
do_install:append {
    if [ -e ${D}/${sysconfdir}/init.d/weston ]; then
        sed -i 's#WESTON_USER=weston#WESTON_USER=diya WESTON_GROUP=weston#' ${D}/${sysconfdir}/init.d/weston
    fi
}
