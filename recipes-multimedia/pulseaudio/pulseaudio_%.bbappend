
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
SRC_URI += "file://pulseaudio"

#inherit update-rc.d

#INITSCRIPT_NAME = "pulseaudio"
#INITSCRIPT_PARAMS = "defaults 75"
#PACKAGECONFIG += " webrtc "

#FILES_${PN} += "${libexecdir}/pulse"

#RDEPENDS_pulseaudio-server_remove = "pulseaudio-module-console-kit 
#                                   pulseaudio-module-x11-cork-request 
#                                   pulseaudio-module-x11-publish 
#                                   pulseaudio-module-x11-xsmp"

do_install:append () {
    install -d ${D}/${sysconfdir}/init.d
    #sed -i 's/load-module module-native-protocol-unix/load-module module-native-protocol-unix auth-anonymous=1/g' ${D}/${sysconfdir}/pulse/system.pa
    # echo "load-module module-echo-cancel source_name=antfx-source aec_method=webrtc aec_args=\"high_pass_filter=1 noise_suppression=0 analog_gain_control=0 digital_gain_control=1\"" >> ${D}/${sysconfdir}/pulse/system.pa
    #echo "set-default-sink alsa_output.usb-GeneralPlus_USB_Audio_Device-00.analog-stereo" >> ${D}/${sysconfdir}/pulse/system.pa
    #echo "set-default-source alsa_input.usb-GeneralPlus_USB_Audio_Device-00.mono-fallback" >> ${D}/${sysconfdir}/pulse/system.pa
 
    install -m 0755 ${WORKDIR}/pulseaudio ${D}/${sysconfdir}/init.d/pulseaudio
}

pkg_postinst_ontarget:${PN} () {
    update-rc.d pulseaudio defaults 75
}

# load-module module-echo-cancel source_name=antfx-source aec_args="high_pass_filter=1 noise_suppression=0 analog_gain_control=0"
# amixer -D pulse set Master/Capture 40%
 