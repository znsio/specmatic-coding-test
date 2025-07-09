package logger

import "github.com/sirupsen/logrus"

type Logger interface {
	Debugf(format string, args ...interface{})
	Infof(format string, args ...interface{})
	Warnf(format string, args ...interface{})
	Errorf(format string, args ...interface{})
	Fatalf(format string, args ...interface{})
}

type logrusLogger struct {
	*logrus.Logger
}

func New() Logger {
	l := logrus.New()
	l.SetFormatter(&logrus.JSONFormatter{})
	l.SetLevel(logrus.InfoLevel)
	return &logrusLogger{Logger: l}
}

func (l *logrusLogger) Debugf(format string, args ...interface{}) { l.Logger.Debugf(format, args...) }
func (l *logrusLogger) Infof(format string, args ...interface{})  { l.Logger.Infof(format, args...) }
func (l *logrusLogger) Warnf(format string, args ...interface{})  { l.Logger.Warnf(format, args...) }
func (l *logrusLogger) Errorf(format string, args ...interface{}) { l.Logger.Errorf(format, args...) }
func (l *logrusLogger) Fatalf(format string, args ...interface{}) { l.Logger.Fatalf(format, args...) }
