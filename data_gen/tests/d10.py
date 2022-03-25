from scipy import signal
import matplotlib.pyplot as plt
import numpy as np

sample = 10000
t = np.linspace(-1, 10, sample, endpoint=False)
i, q, e = signal.gausspulse(t, fc=4, retquad=True, retenv=True)
y = 8 + 6 * q
print(len(y))
plt.plot(t, y)
plt.show()
