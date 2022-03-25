from scipy import signal
import matplotlib.pyplot as plt
import numpy as np

sample = 10000
t = np.linspace(0, 1, sample, endpoint=False)
y = 40 + 37 * signal.square(2 * np.pi * 5 * t)
plt.plot(t, y)
print(len(y))
plt.show()
