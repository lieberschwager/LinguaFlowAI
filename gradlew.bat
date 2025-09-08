@echo off
setlocal

set DEFAULT_JVM_OPTS=

set APP_BASE_NAME=%~n0
set CLASSPATH=

for %%i in ("%APP_HOME%\lib\*.jar") do (
    set CLASSPATH=!CLASSPATH!;%%i
)

java %DEFAULT_JVM_OPTS% -classpath "%CLASSPATH%" org.gradle.wrapper.GradleWrapperMain %*