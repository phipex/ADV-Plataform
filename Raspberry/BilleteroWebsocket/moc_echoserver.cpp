/****************************************************************************
** Meta object code from reading C++ file 'echoserver.h'
**
** Created by: The Qt Meta Object Compiler version 67 (Qt 5.3.2)
**
** WARNING! All changes made in this file will be lost!
*****************************************************************************/

#include "echoserver.h"
#include <QtCore/qbytearray.h>
#include <QtCore/qmetatype.h>
#if !defined(Q_MOC_OUTPUT_REVISION)
#error "The header file 'echoserver.h' doesn't include <QObject>."
#elif Q_MOC_OUTPUT_REVISION != 67
#error "This file was generated using the moc from 5.3.2. It"
#error "cannot be used with the include files from this version of Qt."
#error "(The moc has changed too much.)"
#endif

QT_BEGIN_MOC_NAMESPACE
struct qt_meta_stringdata_EchoServer_t {
    QByteArrayData data[12];
    char stringdata[156];
};
#define QT_MOC_LITERAL(idx, ofs, len) \
    Q_STATIC_BYTE_ARRAY_DATA_HEADER_INITIALIZER_WITH_OFFSET(len, \
    qptrdiff(offsetof(qt_meta_stringdata_EchoServer_t, stringdata) + ofs \
        - idx * sizeof(QByteArrayData)) \
    )
static const qt_meta_stringdata_EchoServer_t qt_meta_stringdata_EchoServer = {
    {
QT_MOC_LITERAL(0, 0, 10),
QT_MOC_LITERAL(1, 11, 6),
QT_MOC_LITERAL(2, 18, 0),
QT_MOC_LITERAL(3, 19, 17),
QT_MOC_LITERAL(4, 37, 9),
QT_MOC_LITERAL(5, 47, 14),
QT_MOC_LITERAL(6, 62, 10),
QT_MOC_LITERAL(7, 73, 15),
QT_MOC_LITERAL(8, 89, 18),
QT_MOC_LITERAL(9, 108, 7),
QT_MOC_LITERAL(10, 116, 20),
QT_MOC_LITERAL(11, 137, 18)
    },
    "EchoServer\0closed\0\0socketMsgDetected\0"
    "socketMsg\0sendIntMessage\0iBillValue\0"
    "onNewConnection\0processTextMessage\0"
    "message\0processBinaryMessage\0"
    "socketDisconnected"
};
#undef QT_MOC_LITERAL

static const uint qt_meta_data_EchoServer[] = {

 // content:
       7,       // revision
       0,       // classname
       0,    0, // classinfo
       7,   14, // methods
       0,    0, // properties
       0,    0, // enums/sets
       0,    0, // constructors
       0,       // flags
       2,       // signalCount

 // signals: name, argc, parameters, tag, flags
       1,    0,   49,    2, 0x06 /* Public */,
       3,    1,   50,    2, 0x06 /* Public */,

 // slots: name, argc, parameters, tag, flags
       5,    1,   53,    2, 0x0a /* Public */,
       7,    0,   56,    2, 0x08 /* Private */,
       8,    1,   57,    2, 0x08 /* Private */,
      10,    1,   60,    2, 0x08 /* Private */,
      11,    0,   63,    2, 0x08 /* Private */,

 // signals: parameters
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString,    4,

 // slots: parameters
    QMetaType::Void, QMetaType::Int,    6,
    QMetaType::Void,
    QMetaType::Void, QMetaType::QString,    9,
    QMetaType::Void, QMetaType::QByteArray,    9,
    QMetaType::Void,

       0        // eod
};

void EchoServer::qt_static_metacall(QObject *_o, QMetaObject::Call _c, int _id, void **_a)
{
    if (_c == QMetaObject::InvokeMetaMethod) {
        EchoServer *_t = static_cast<EchoServer *>(_o);
        switch (_id) {
        case 0: _t->closed(); break;
        case 1: _t->socketMsgDetected((*reinterpret_cast< QString(*)>(_a[1]))); break;
        case 2: _t->sendIntMessage((*reinterpret_cast< const int(*)>(_a[1]))); break;
        case 3: _t->onNewConnection(); break;
        case 4: _t->processTextMessage((*reinterpret_cast< QString(*)>(_a[1]))); break;
        case 5: _t->processBinaryMessage((*reinterpret_cast< QByteArray(*)>(_a[1]))); break;
        case 6: _t->socketDisconnected(); break;
        default: ;
        }
    } else if (_c == QMetaObject::IndexOfMethod) {
        int *result = reinterpret_cast<int *>(_a[0]);
        void **func = reinterpret_cast<void **>(_a[1]);
        {
            typedef void (EchoServer::*_t)();
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&EchoServer::closed)) {
                *result = 0;
            }
        }
        {
            typedef void (EchoServer::*_t)(QString );
            if (*reinterpret_cast<_t *>(func) == static_cast<_t>(&EchoServer::socketMsgDetected)) {
                *result = 1;
            }
        }
    }
}

const QMetaObject EchoServer::staticMetaObject = {
    { &QObject::staticMetaObject, qt_meta_stringdata_EchoServer.data,
      qt_meta_data_EchoServer,  qt_static_metacall, 0, 0}
};


const QMetaObject *EchoServer::metaObject() const
{
    return QObject::d_ptr->metaObject ? QObject::d_ptr->dynamicMetaObject() : &staticMetaObject;
}

void *EchoServer::qt_metacast(const char *_clname)
{
    if (!_clname) return 0;
    if (!strcmp(_clname, qt_meta_stringdata_EchoServer.stringdata))
        return static_cast<void*>(const_cast< EchoServer*>(this));
    return QObject::qt_metacast(_clname);
}

int EchoServer::qt_metacall(QMetaObject::Call _c, int _id, void **_a)
{
    _id = QObject::qt_metacall(_c, _id, _a);
    if (_id < 0)
        return _id;
    if (_c == QMetaObject::InvokeMetaMethod) {
        if (_id < 7)
            qt_static_metacall(this, _c, _id, _a);
        _id -= 7;
    } else if (_c == QMetaObject::RegisterMethodArgumentMetaType) {
        if (_id < 7)
            *reinterpret_cast<int*>(_a[0]) = -1;
        _id -= 7;
    }
    return _id;
}

// SIGNAL 0
void EchoServer::closed()
{
    QMetaObject::activate(this, &staticMetaObject, 0, 0);
}

// SIGNAL 1
void EchoServer::socketMsgDetected(QString _t1)
{
    void *_a[] = { 0, const_cast<void*>(reinterpret_cast<const void*>(&_t1)) };
    QMetaObject::activate(this, &staticMetaObject, 1, _a);
}
QT_END_MOC_NAMESPACE
