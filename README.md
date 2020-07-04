```
./build.sh setup

./build.sh test
./build.sh package

./run.sh add 12 13
./run.sh cat < README.md

./run.sh cat2 < README.md
./run.sh cat2 -A=1 < README.md

rake && ./run.sh {args}
```
