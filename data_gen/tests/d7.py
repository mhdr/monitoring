from scipy.signal import sweep_poly
import numpy as np
import matplotlib.pyplot as plt

sample = 10000
p = np.poly1d([0.025, -0.36, 1.25, 2.0])
t = np.linspace(0, 10, sample)
y = 5 + 4 * sweep_poly(t, p)
print(len(y))
plt.plot(t, y)
plt.show()
