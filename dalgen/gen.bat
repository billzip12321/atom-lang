@echo off
echo 开始生成的DAS代码[build.xml]...
rm -rf ./target
rm -rf ./logs
call ant -f ./build.xml
rem exit
