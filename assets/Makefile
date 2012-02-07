CONV := ./makepng
PLAIN := lockscreen-gen-512x512.png lockscreen-gen-72x72.png \
			lockscreen-gen-48x48.png lockscreen-gen-36x36.png

all : plain
plain : $(PLAIN)

lockscreen-gen-512x512.png : lockscreen.svg
	$(CONV) lockscreen-gen-512x512.png
	optipng -q generated/lockscreen-gen-512x512.png
lockscreen-gen-72x72.png : lockscreen.svg
	$(CONV) lockscreen-gen-72x72.png
	optipng -q generated/lockscreen-gen-72x72.png
lockscreen-gen-48x48.png : lockscreen.svg
	$(CONV) lockscreen-gen-48x48.png
	optipng -q generated/lockscreen-gen-48x48.png
lockscreen-gen-36x36.png : lockscreen.svg
	$(CONV) lockscreen-gen-36x36.png
	optipng -q generated/lockscreen-gen-36x36.png

clean : 
	rm -rf generated/*
