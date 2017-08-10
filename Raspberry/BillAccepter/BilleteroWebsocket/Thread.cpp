

// Own
#include <Thread.hpp>
#include <qdebug.h>

Thread::Thread(QObject * parent):
    QObject(parent), _status(Stopped)
{
    //qDebug()<<"Thread::Thread";
    _pThread = NULL;
}

Thread::~Thread()
{
    //qDebug()<<"Thread::~Thread()";
    stop();
    // if (_pThread != NULL)
    //     pthread_join(*_pThread, NULL);
}

void Thread::start()
{
    //qDebug()<<"Thread::start";
    if (_pThread == NULL && pthread_create((_pThread = new pthread_t), NULL, run, (void*)this) != 0)
        _pThread = NULL;
}

void Thread::stop()
{
    //qDebug()<<"Thread::stop";
    _status = Stopped;
}

void* Thread::run(void*args)
{
    //qDebug()<<"Thread::run";
    Thread * pThread = static_cast<Thread *>(args);

    if (pThread->_status == Stopped)
    {
        pThread->_status = Running;
        pThread->run();
    }

    pThread->threadStopped();
    return NULL;
}

void Thread::threadStopped()
{
    //qDebug()<<"Thread::threadStopped";
    delete _pThread;
    _pThread = NULL;
    _status = Stopped;
}
