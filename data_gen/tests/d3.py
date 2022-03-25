import matplotlib.pyplot as plt
import numpy as np

Fs = 1000
f = 1
sample = 1000
x = np.arange(sample)
m = np.arange(0, 100, 0.1)
y = 500 + m * np.sin(2 * np.pi * f * x / Fs)
print(len(y))
plt.plot(x, y)
plt.xlabel('sample(n)')
plt.ylabel('voltage(V)')
plt.show()
