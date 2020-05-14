This is a very easy analizer/visualizer for CSV (Comma Separeted Values) data, so it's used to open text files written in this format:
ColoumnTitle0  ColoumnTitle1   ColoumnTitle2 ...
value0  value0  value0  ...
value1  value1  value1  ...
...

To open a file (as .txt), go in file menu and navigate to your file.
You need also to enter the values of the divider (i.e. , space, tab ecc). In particolar, for files producted with the gas sensor equipment the divider is "tab" (written in visible characters as \t, so enter \t).

To display a chart, go to display->displayIJ.
Now enter the index of the columns than you want display (i.e. "3 5"). In this way, column 3 is plotted versus column 5 (It starts from 0).
In the file producted from the actual gas sensor equipment, the first 2 column are date and hours so they can't be plotted (doesnt' matter, just don't use them).

By now are loaded some noise reduction filters quite clever. To reduce the noise are quite useful.

Fast explanation:
->Box Car Order 0: Interpolation in a local sample to a costant value
->Box Car Order 1: Interpolation in a local sample to a line.
->Median: Median blur in a local sample (doesn't blur fast edge).
->Derivate Order1: Finite derivate.

Just to know: Noise reduction need to know the size of the local sample, (aka how far data are correlated in the noise, and so the intensity of the filter). 5 to 10 are a reasonable values.

N.B: Filter are added to the filtered data, so you can combine many in the single chart. To reset, click on Filters->Erase Filters.

Other things are quite intuitive.
Stay tuned for updates. 



