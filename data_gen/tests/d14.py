import matplotlib.pyplot as plt
import numpy as np

sample = 10000
sample1 = 7000
sample2 = 1000
sample3 = 2000

t = np.arange(sample)

y1 = np.zeros(sample1)
y2 = np.ones(sample2)
y3 = np.zeros(sample3)
y = np.concatenate((y1, y2, y3))
print(len(y))
plt.plot(t, y)
plt.show()
