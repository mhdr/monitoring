from scipy.signal import chirp, spectrogram
import matplotlib.pyplot as plt
import numpy as np

sample = 10000
t = np.linspace(0, 10, sample)
y = 150 + 35 * chirp(t, f0=12, f1=0.5, t1=10, method='linear')
plt.plot(t, y)
print(len(y))
plt.title("Linear Chirp, f(0)=6, f(10)=1")
plt.xlabel('t (sec)')
plt.show()
