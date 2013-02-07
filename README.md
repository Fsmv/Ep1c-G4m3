Port Mortem
======
To offer an explanation of what this is and why I made it, I started this project to gain a better knowledge of Java.

The fact that I was (and still am) learning means that most of the code contained here isn't very good and could be made much better. However, there are a few things I'm particularly proud of. The way I've done the storage of maps, tiles, and entities means that to add new graphics and functionality one only has to add as class in the appropriate place and add an image with the appropriate format. I didn't figure out dynamic class loading before I decided to stop developing this but it's still very easy to integrate new components. However, I think the part I'm most proud of was also this project's downfall; I was trying to make everything entirely general, to have no restrictions on what you could add to the game later on. This isn't the normal approach to development becuase it takes a lot of extra work to account for every possibility but I had mostly done it.

I never got around to actually making this into a game but the engine is complete, if I rembember correctly. In the master branch I have some older code from the first time I finished the engine. When I started running it, I realized it was way too slow for a game. Just moving around on a map was going at less than 10 fps on my computer at the time. That's why I decided to rewrite the engine as completely I could to change what I though was slowing it down. In the rewrite I changed the way I rendered images and a lot of other things, it has the most recent code and it's kept in the rewrite branch of this repository. The problem is, once I finished setting up the new rendering system it was still too slow. I may have gotten more fps out of it, I don't really remember, but it was still too slow. Now I realize that my problem was trying to do everything in a single thread. I should have been doing most of my work outside of the rendering thread and I would have been able to get more fps out of the game.

Over the time it took to put this together I made lots of searches about Java and talked to people in an irc channel quite a bit. This taught me a lot about Java as well as programming in general. This project led me to most of the programming skills which I employ today.

The original read me has been kept unchanged below, I even left the typo.

Contributing
======

Ask
------
You _might_ want to submit a [issue](https://github.com/Fsmv/Ep1c-G4m3/issues/new) with the lavel 'Suggestion' first to make sure I'll merge it if you make it.

Code
------
 1. Fork
 2. Branch (`git checkout -b name`)
 3. Commit (`git commit -am "Added..."`)
 4. Push (`git push origin name`)
 5. Make an [issue](https://github.com/Fsmv/Ep1c-G4m3/issues/new) with the label 'Contribution'
 6. Wait; If I like it, I'll merge it.

Join
------
If you would like to make regular contributions message me with the subject 'Contributing to Ep1c G4m3.' We'll go from there and I'll probably add you as a collaborator.
