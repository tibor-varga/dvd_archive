# dvd_archive
archive cds, dvds

Type      Format    Drive       Tool      Example
CD        cdda      /dev/sr0    cdrdao    cdrdao copy --device /dev/sr0 aa.bin
DVD Data  udf       /dev/sr0    cp        cp -r /dev/sr0/  /tmp/xxx
DVD Video iso9660   /dev/sr0    dd        dd if=/dev/sr2 of=/tmp/dvd2.iso

Useful commands:
cat /proc/sys/dev/cdrom/info
CD-ROM information, Id: cdrom.c 3.20 2003/12/17

drive name:		sr2	sr1	sr0
drive speed:		125	48	40
drive # of slots:	1	1	1
Can close tray:		1	1	1
Can open tray:		1	1	1
Can lock tray:		1	1	1
Can change speed:	1	1	1
Can select disk:	0	0	0
Can read multisession:	1	1	1
Can read MCN:		1	1	1
Reports media changed:	1	1	1
Can play audio:		1	1	1
Can write CD-R:		1	1	1
Can write CD-RW:	1	1	1
Can read DVD:		1	1	1
Can write DVD-R:	1	1	1
Can write DVD-RAM:	1	1	1
Can read MRW:		0	0	0
Can write MRW:		0	0	0
Can write RAM:		1	1	1


