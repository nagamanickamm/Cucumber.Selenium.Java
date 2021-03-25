search=$(echo `whereis google-chrome`)
echo "Value of search: $search"
if [[ $search == "google-chrome:" ]]; then
        wget https://dl.google.com/linux/direct/google-chrome-stable_current_x86_64.rpm
        sudo yum -y install ./google-chrome-stable_current_*.rpm
fi