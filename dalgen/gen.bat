@echo off
echo ��ʼ���ɵ�DAS����[build.xml]...
rm -rf ./target
rm -rf ./logs
call ant -f ./build.xml
rem exit
