#!/bin/bash
# mount | grep "dev/sr" | cut -d " " -f 3,5
# /media/vt/Filmek iso9660
# /media/vt/YOU_HAVE_GOT_MAIL udf
# /media/vt/DVD udf
for mounted in `mount | grep "dev/sr" | cut -d " " -f 1,3,5 |  tr " " ,` ;  do  
        comma_separated_mounted=`echo $mounted | tr , " "`;
        read drive path typ <<< "$comma_separated_mounted";

        if [ "$typ" = "udf" ];  then 
                # https://wiki.ubuntuusers.de/DVDs_manuell_rippen/      
                tracknum=`lsdvd -q /dev/sr1 |grep Longest  | tail -1 | cut -d ":" -f 2 | tr -d " "`;
                timestamp=$(date +%s);
                dvdtitle=`echo $path |  cut -d "/" -f 4`;
        filename="$dvdtitle"_"$timestamp";
                echo "path: $path, drive: $drive, typ:$typ, longest track: $tracknum, echo filename: $filename";

                mplayer  dvd://$tracknum  -dvd-device $drive -v -dumpstream -dumpfile "/home/vt/dvd_project/$filename".vob & 
                # tomorites vob->mp4
                # cp subtitle 
                
    else 
                echo "$drive    cp -r $path/* /home/vt/dvd_project";     
                cp -r $path/* /home/vt/dvd_project  & 
        fi

done
