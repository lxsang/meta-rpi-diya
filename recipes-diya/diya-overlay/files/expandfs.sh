#! /bin/sh
line=$(
sed -e 's/\s*\([\+0-9a-zA-Z]*\).*/\1/' << EOF | fdisk /dev/mmcblk0 | grep /dev/mmcblk0p4
p
q
EOF
)

echo "Partition: $line"
#start_sector=$(echo "$line" | cut -d' ' -f14)
start_sector=$(echo "$line" | cut -d' ' -f15)
echo "Start sector is: $start_sector"

if [ -z "$start_sector" ]; then
	echo "Cannot find the start sector"
	exit 1
fi

echo "Expanding the partition"
sed -e 's/\s*\([\+0-9a-zA-Z]*\).*/\1/' << EOF | fdisk /dev/mmcblk0
d # delete partition
4 # number 4
n # new partition
p # primary partition
4 # partition number 4
$start_sector
  # default - end of disk 
p # print the in-memory partition table
w # write the partition table
q # and we're done
EOF