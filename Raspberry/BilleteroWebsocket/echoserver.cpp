#include "echoserver.h"
#include "QtWebSockets/qwebsocketserver.h"
#include "QtWebSockets/qwebsocket.h"
#include <QtCore/QDebug>
#include "ID003.hpp"

QT_USE_NAMESPACE

EchoServer::EchoServer(quint16 port, bool debug, QObject *parent) :
    QObject(parent),
    m_pWebSocketServer(new QWebSocketServer(QStringLiteral("Echo Server"),
                                            QWebSocketServer::NonSecureMode, this)),
    m_debug(debug)
{
    if (m_pWebSocketServer->listen(QHostAddress::Any, port)) {

        if (m_debug){
            qDebug() << "Echoserver listening on port:" << port;
        }

        connect(m_pWebSocketServer, &QWebSocketServer::newConnection, this, &EchoServer::onNewConnection);
        connect(m_pWebSocketServer, &QWebSocketServer::closed, this, &EchoServer::closed);
    }   
}

EchoServer::~EchoServer()
{
    m_pWebSocketServer->close();
    qDeleteAll(m_clients.begin(), m_clients.end());
}

void EchoServer::onNewConnection()
{
    if (m_debug){
        qDebug() << "New socket connection";
    }

    QWebSocket *pSocket = m_pWebSocketServer->nextPendingConnection();

    connect(pSocket, &QWebSocket::textMessageReceived, this, &EchoServer::processTextMessage);
    connect(pSocket, &QWebSocket::binaryMessageReceived, this, &EchoServer::processBinaryMessage);
    connect(pSocket, &QWebSocket::disconnected, this, &EchoServer::socketDisconnected);

    m_clients << pSocket;
}

void EchoServer::processTextMessage(QString message)
{
    //QWebSocket *pClient = qobject_cast<QWebSocket *>(sender());
    if (m_debug){
        qDebug() << "Received:" << message;
        emit socketMsgDetected(message);                                      /*emit*/
    }
    /*if (pClient) {
        pClient->sendTextMessage(message);
    }*/
    //sendMessage(pClient,message);
    //broadCast(message);
}

void EchoServer::broadCast(QString message)
{

    for( int i=0; i<m_clients.count(); ++i ){
        QWebSocket *pClient = qobject_cast<QWebSocket *>(m_clients.at(i));
        sendMessage(pClient,message);
    }

}

void EchoServer::sendMessage(QWebSocket* pClient,QString message)
{

    if(pClient){
        pClient->sendTextMessage(message);
        qDebug() << "Echo:" << message;
    }

}

void EchoServer::processBinaryMessage(QByteArray message)
{
    QWebSocket *pClient = qobject_cast<QWebSocket *>(sender());

    if(m_debug){
        qDebug() << "Binary message received:" << message;
    }

    if(pClient) {
        pClient->sendBinaryMessage(message);
    }

}

void EchoServer::socketDisconnected()
{
    QWebSocket *pClient = qobject_cast<QWebSocket *>(sender());

    if(m_debug){
        qDebug() << "Socket disconnected!"; //<< pClient;
    }

    if(pClient) {
        m_clients.removeAll(pClient);
        pClient->deleteLater();
        emit socketMsgDetected("off");                                      /*emit*/
    }

}

// Método que se ejecuta al emitirse la señal billDetected
void EchoServer::sendIntMessage(const int& iBillValue)
{
    QString message = QString::number(iBillValue);

    broadCast(message);
}
