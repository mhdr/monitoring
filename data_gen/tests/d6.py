from scipy import signal
import matplotlib.pyplot as plt
import numpy as np

sample = 10000
t = np.linspace(0, 1, sample, endpoint=False)
plt.figure()
sig = np.sin(2 * np.pi * t)
y = 40 + 20 * signal.square(2 * np.pi * 30 * t, duty=(sig + 1) / 2)
print(len(y))
plt.plot(t, y)
plt.show()
