import numpy as np
from scipy import signal
from scipy.signal import sweep_poly
from scipy.signal import chirp


class Value1:

    def __init__(self):
        Fs = 10000
        f = 1
        self.sample = 10000
        x = np.arange(self.sample)
        self.y = 10 + np.sin(2 * np.pi * f * x / Fs)
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value2:

    def __init__(self):
        Fs = 10000
        f = 1
        self.sample = 10000
        x = np.arange(self.sample)
        self.y = 400 + 2 * np.sin(2 * np.pi * f * x / Fs)
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value3:

    def __init__(self):
        Fs = 1000
        f = 1
        self.sample = 1000
        x = np.arange(self.sample)
        m = np.arange(0, 100, 0.1)
        self.y = 500 + m * np.sin(2 * np.pi * f * x / Fs)
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value4:

    def __init__(self):
        self.sample = 2000
        t = np.linspace(0, 1, self.sample)
        self.y = 4 + signal.sawtooth(2 * np.pi * 5 * t)
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value5:

    def __init__(self):
        self.sample = 10000
        t = np.linspace(0, 1, self.sample, endpoint=False)
        self.y = 40 + 37 * signal.square(2 * np.pi * 5 * t)
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value6:

    def __init__(self):
        self.sample = 10000
        t = np.linspace(0, 1, self.sample, endpoint=False)
        sig = np.sin(2 * np.pi * t)
        self.y = 40 + 20 * signal.square(2 * np.pi * 30 * t, duty=(sig + 1) / 2)
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value7:

    def __init__(self):
        self.sample = 10000
        p = np.poly1d([0.025, -0.36, 1.25, 2.0])
        t = np.linspace(0, 10, self.sample)
        self.y = 5 + 4 * sweep_poly(t, p)
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value8:

    def __init__(self):
        self.sample = 10000
        t = np.linspace(-1, 4, self.sample, endpoint=False)
        i, q, e = signal.gausspulse(t, fc=4, retquad=True, retenv=True)
        self.y = 40 * e
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value9:

    def __init__(self):
        self.sample = 10000
        t = np.linspace(-1, 2, self.sample, endpoint=False)
        i, q, e = signal.gausspulse(t, fc=4, retquad=True, retenv=True)
        self.y = 50 + 20 * i
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value10:

    def __init__(self):
        self.sample = 10000
        t = np.linspace(-1, 10, self.sample, endpoint=False)
        i, q, e = signal.gausspulse(t, fc=4, retquad=True, retenv=True)
        self.y = 8 + 6 * q
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value11:

    def __init__(self):
        self.sample = 10000
        t = np.linspace(-1, 1, self.sample, endpoint=False)
        i, q, e = signal.gausspulse(t, fc=4, retquad=True, retenv=True)
        self.y = 97 * e
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value12:

    def __init__(self):
        self.sample = 10000
        t = np.linspace(0, 10, self.sample)
        self.y = 150 + 35 * chirp(t, f0=12, f1=0.5, t1=10, method='linear')
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value13:

    def __init__(self):
        self.sample = 10000
        t = np.linspace(0, 10, self.sample)
        self.y = 230 + 17 * chirp(t, f0=12, f1=0.5, t1=4, method='linear')
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)


class Value14:

    def __init__(self):
        self.sample = 10000
        sample1 = 7000
        sample2 = 1000
        sample3 = 2000

        y1 = np.zeros(sample1)
        y2 = np.ones(sample2)
        y3 = np.zeros(sample3)
        self.y = np.concatenate((y1, y2, y3))
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return int(result)


class Value15:

    def __init__(self):
        self.sample = 10000
        sample1 = 2000
        sample2 = 1000
        sample3 = 1000
        sample4 = 100
        sample5 = 5900

        y1 = np.ones(sample1)
        y2 = np.zeros(sample2)
        y3 = np.ones(sample3)
        y4 = np.zeros(sample4)
        y5 = np.ones(sample5)
        self.y = np.concatenate((y1, y2, y3, y4, y5))
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return int(result)

class Value16:

    def __init__(self):
        self.sample = 40000
        p = np.poly1d([0.025, -0.36, 1.25, 2.0])
        t = np.linspace(0, 10, self.sample)
        self.y = 16 + 8 * sweep_poly(t, p)
        self.index = 0

    def next(self):
        result = self.y[self.index]
        if self.index < self.sample - 1:
            self.index += 1
        else:
            self.index = 0

        return round(result, 2)