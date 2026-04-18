#!/usr/bin/env python3
"""Copy Royal model JSONs from 1.19.4 ref into our namespace, rewriting texture paths.

1.19.4 layout:   fantasyfurniture:block/models/royal/X          (texture)
                 fantasyfurniture:block/particles/royal         (particle)

Our layout:      fantasyfurniture_royal:block/X                  (texture)
                 fantasyfurniture_royal:block/particle           (particle)
"""
import re
import sys
from pathlib import Path

SRC = Path("../FantasyFurniture-1.19.4-ref/src/main/resources/assets/fantasyfurniture/models/block/royal")
DST = Path("src/main/resources/assets/fantasyfurniture_royal/models/block")

DST.mkdir(parents=True, exist_ok=True)

count = 0
for f in sorted(SRC.glob("*.json")):
    text = f.read_text(encoding="utf-8")
    text = text.replace("fantasyfurniture:block/models/royal/", "fantasyfurniture_royal:block/")
    text = text.replace("fantasyfurniture:block/particles/royal", "fantasyfurniture_royal:block/particle")
    (DST / f.name).write_text(text, encoding="utf-8")
    count += 1

print(f"Copied {count} Royal model JSONs with texture paths rewritten")
