import matplotlib.pyplot as plt
import numpy as np

Fs = 10000
f = 1
sample = 10000
x = np.arange(sample)
y = 400 + 2 * np.sin(2 * np.pi * f * x / Fs)
print(len(y))
plt.plot(x, y)
plt.xlabel('sample(n)')
plt.ylabel('voltage(V)')
plt.show()
