SUMMARY = "Extract CPU and Memory Inventory from SMSMBIOS Table and PECI"
DESCRIPTION = "This package parses SMBIOS tables, reads Intel CPU PIROM and PECI and provides a dedicated IPMI blob to receive SMBIOS tables sent from LinuxBoot"
HOMEPAGE = "http://github.com/openbmc/smbios-mdr"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3fc50a88d0a364313df4b21ef20c29e"
DEPENDS += " \
    boost \
    systemd \
    sdbusplus \
    phosphor-dbus-interfaces \
    phosphor-logging \
    "
SRCREV = "c6d87a5cccbb13b9c7b01e90be6e61c5397c5917"
PACKAGECONFIG ?= "cpuinfo"
PACKAGECONFIG[smbios-no-dimm] = "-Ddimm-dbus=disabled,-Ddimm-dbus=enabled"
PACKAGECONFIG[cpuinfo] = "-Dcpuinfo=enabled,-Dcpuinfo=disabled,libpeci i2c-tools"
PACKAGECONFIG[smbios-ipmi-blob] = "-Dsmbios-ipmi-blob=enabled,-Dsmbios-ipmi-blob=disabled,phosphor-ipmi-blobs"
PV = "1.0+git${SRCPV}"
PR = "r1"

SRC_URI = "git://github.com/openbmc/smbios-mdr.git;branch=master;protocol=https"

S = "${WORKDIR}/git"
SYSTEMD_SERVICE:${PN} += "smbios-mdrv2.service"
SYSTEMD_SERVICE:${PN} += "${@bb.utils.contains('PACKAGECONFIG', 'cpuinfo', 'xyz.openbmc_project.cpuinfo.service', '', d)}"

inherit meson pkgconfig systemd

FILES:${PN} += "${libdir}/blob-ipmid"
