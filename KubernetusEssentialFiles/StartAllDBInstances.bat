cd UserServiceDB
cmd /c RebuildUserServiceDB.bat
cd ../GameServiceDB
cmd /c RebuildGameServiceDB.bat
cd ../GameServiceDB2
cmd /c RebuildGameServiceDB.bat
cd ../AuthServiceRedisCache
cmd /c RebuildRedis.bat
