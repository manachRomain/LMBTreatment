# -*- coding: utf-8 -*-

import matplotlib.pyplot as plt
import matplotlib
import numpy as np
import pylab as pl

from matplotlib import rc

from pylab import *
import matplotlib.colors
from matplotlib import transforms

fig = figure(figsize=(2.6,2.6))

## Polar

polar(True)
ax = plt.gca()

## Range x

ax.set_rlim(0,1.2)

## Range angle

angles = np.arange(0, 360, 20)

## Grid angle

plt.thetagrids(angles, labels=None,fontsize=6)
ax.grid(color = '#316931', linewidth = 0.1, linestyle = '--')

## Range 2 x

ax.set_rgrids([0.2,0.4,0.6,0.8,1,],angle=0,fontsize=4, alpha=0, color='white')

## Titre

plt.title("Mt/Ms", fontsize=10, y=1.075)

## Trace

row=np.genfromtxt("C:/Users/manachr/Documents/LMB/mesures/data.txt")
phi4= np.radians(row[:,5])
Hc4=row[:,4]
plt.plot(phi4, Hc4, 'o-', linewidth=1.5,ms=2.5, label="30 nm")

## Légende

leg = plt.legend(fancybox=True,loc='lower right',fontsize=10,bbox_to_anchor=(1.2, 0))
leg.get_frame().set_alpha(0.7)

factor = 1.1
d = ax.get_yticks()[-1] * factor
r_tick_labels = [0] + ax.get_yticks()
r_ticks = (np.array(r_tick_labels) ** 2 + d ** 2) ** 0.5
theta_ticks = np.arcsin(d / r_ticks) + np.pi / 2
r_axlabel = (np.mean(r_tick_labels) ** 2 + d ** 2) ** 0.5
theta_axlabel = np.arcsin(d / r_axlabel) + np.pi / 2

# fixed offsets in x
offset_spine = transforms.ScaledTranslation(-30, 0, ax.transScale) # position de l'axe
offset_ticklabels = transforms.ScaledTranslation(97, 1.9, ax.transScale) # position des valeurs
offset_axlabel = transforms.ScaledTranslation(-30, 0, ax.transScale) # position du Hc

# apply these to the data coordinates of the line/ticks
trans_spine = ax.transData + offset_spine
trans_ticklabels = trans_spine + offset_ticklabels
trans_axlabel = trans_spine + offset_axlabel

# plot the 'spine'
ax.plot(theta_ticks, r_ticks, '-_k', transform=trans_spine,
             clip_on=False, alpha=0.75, color = 'black')

# plot the 'tick labels'
for ii in range(len(r_ticks)):
    ax.text(theta_ticks[ii] , r_ticks[ii], "%.1f" % r_tick_labels[ii] + ' '*2 + '.'*30,
                 ha="right", va="center", clip_on=False,
                 transform=trans_ticklabels, alpha=0.75 , color = 'black')

# plot the 'axis label'
ax.text(theta_axlabel, r_axlabel, ' ', fontsize=10,
             ha='right', va='center', clip_on=False, transform=trans_axlabel, alpha=0.75, color = 'black')

## Save
pl.savefig("C:/Users/manachr/Documents/LMB/mesures/plot.pdf",bbox_inches='tight')
