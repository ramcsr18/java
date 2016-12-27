#!/usr/local/bin/bash -x
function
usage ()
{
	echo "Family name is required"
    echo "Usage: $0 <family>\n"
    echo "   <family> - Fusion apps family name\n"
}

if [ [-z "$AVR" ]]
then
    echo "ERROR: Must be run in a valid view"
    exit 1 
fi 

cd $ADE_PRODUCT_ROOT declare family = "$1" 
if [[ -z "$family" ]]
then
	echo - n "Please enter valid family name [ic|atf|crm|com] :"
	read family 
	if [[ -z "$family" ]]
	then
    	usage
    	exit 1
    fi
fi
echo "Processing '$family' family" 

if[[ ! -d "tmp/$family/orig" ]]
then 
	mkdir "tmp/$family/orig" 
	cp "$family/deploy/*.ear" "tmp/$family/orig"
fi
cd "tmp/$family"
for ear in $(ls "orig/*.ear")
do
	ear=${ear/orig/}
	ear=${ear/\//}
	earDir="$ear.exp"

	if [[ ! -d "$earDir" ]]
	then
		mkdir "$earDir"
        cd "$earDir"
        jar -xvf "../orig/$ear"
        cd -

        for war in $(ls "$earDir/*.war")
        do
	        war=${war/$earDir/}
	        war=${war/\//}
	        warDir="$war.exp"
	        if [[ -d "$warDir" ]]
	        then
	        	mkdir "$warDir"
	        	cd "$warDir"
	            jar -xvf "../$earDir/$war"
			fi
		done
	fi
    echo  "Processing $ear"
done
