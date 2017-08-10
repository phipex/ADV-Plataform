#-------------------------------------------------
#
# Project created by QtCreator 2017-07-01T10:31:46
#
#-------------------------------------------------

QT       += core websockets
QT       -= gui

TARGET = qtLectorCedWebsocket
TEMPLATE = app

SOURCES += \
    main.cpp \
    echoserver.cpp \
    idscanner.cpp

HEADERS += \
    echoserver.h \
    idscanner.h

#EXAMPLE_FILES += echoclient.html

target.path = $$[QT_INSTALL_EXAMPLES]/websockets/echoserver
INSTALLS += target
