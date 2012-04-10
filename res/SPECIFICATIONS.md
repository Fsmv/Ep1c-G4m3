Resource Specifications
======
This file describes how data is gathered from the resource image pixel colors.
Throughout the rest of this file, k is an integer (in the code k is incremented until the width of the image is reached).
By convention, strings written in the colors as hex values should be all lower-case. While this has nothing to do with functionality, it is just good design.

Helpful utilities:

 * [String to hex converter](http://www.string-functions.com/string-hex.aspx)
 * [Decimal to hex converer](http://easycalculation.com/decimal-converter.php)

Entity
------
The top row of pixels is for data.

 * (0, 0) color is 0xWWHH00 where WW is width in hex and HH is height in hex
 * (1-4 * k, 0) color is the name for animation number k
   1. Convert your [String into hex](http://www.string-functions.com/string-hex.aspx) (12 char max)
   2. Split the output into groups of six numbers, each group of six is a pixel color
   3. If you don't fill up 12 characters pad the rest with 0
 * (5 * k, 0) color is the number of frames in the previously named animation
   * Frame numbers go left to right, when it hits the end of the image it starts at the left in the next row
   * Lead with 0s for example: if you have 3 frames your color would be 0x000003
 * The program stops reading the data line when it sees 0xFFFFFF

The rest of the image is for the frames.

 * Image width must be divisable by the frame width
 * Image height-1 must be divisable by the frame height
 * Frames should have no padding between them

Resource File
------
Should be named rsc.png and be in the entity's folder.

The top two rows of pixels are for data.

 * (0-1 * k, 0-1) color is the name for the resource. (reads left to right {(0, 0), (0, 1), (1, 0), (1, 1)})
 * (2 * k, 0) color is 0xXXYY00 where XX and YY are x and y positions of the resource data in hex
 * (2 * k, 1) color is 0xWWHH00 where WW and HH are width and height of the resource data in hex
 * Stops reading when it sees color(x, 0) && color(x, 1) == 0xffffff

The image can be any size, but make sure to put a 1x2 pixel white line after the data if you're going to put image parts at the top of the image.

Tiles file
------
Named tiles.png and is in the res directory

The top row of pixels is for data.

 * (0, 0) color is 0xSS0000 where SS is side length in hex
 * (1-4 * k, 0) is the name for tile number k
   1. Convert your [String into hex](http://www.string-functions.com/string-hex.aspx) (12 char max)
   2. Split the output into groups of six numbers, each group of six is a pixel color
   3. If you don't fill up 12 characters pad the rest with 0
 * The program stops reading the data line when it sees 0xFFFFFF
