import matplotlib.pyplot as plt
import numpy as np


Fs = 10000
f = 1
sample = 10000
x = np.arange(sample)
y = 10 + np.sin(2 * np.pi * f * x / Fs)
plt.plot(x, y)
print(len(y))
plt.xlabel('sample(n)')
plt.ylabel('voltage(V)')
plt.show()