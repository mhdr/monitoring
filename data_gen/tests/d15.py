import matplotlib.pyplot as plt
import numpy as np

sample = 10000
sample1 = 2000
sample2 = 1000
sample3 = 1000
sample4 = 100
sample5 = 5900

t = np.arange(sample)

y1 = np.ones(sample1)
y2 = np.zeros(sample2)
y3 = np.ones(sample3)
y4 = np.zeros(sample4)
y5 = np.ones(sample5)
y = np.concatenate((y1, y2, y3, y4, y5))
print(len(y))
plt.plot(t, y)
plt.show()
