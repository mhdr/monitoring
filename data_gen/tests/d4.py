from scipy import signal
import matplotlib.pyplot as plt
import numpy as np

sample = 10000
t = np.linspace(0, 1, sample)
y = 4 + signal.sawtooth(2 * np.pi * 5 * t)
print(len(y))
plt.plot(t, y)
plt.show()
