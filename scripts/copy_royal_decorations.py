#!/usr/bin/env python3
"""Copy Royal DECORATION model JSONs + textures from 1.19.4 ref into our namespace.

1.19.4 layout:
    models/block/decorations/royal_X.json
    textures/block/models/decorations/royal_X.png
    textures/block/particles/royal.png

Our layout (within fantasyfurniture_royal namespace):
    models/block/decorations/X.json             (drop royal_ prefix)
    textures/block/decorations/X.png            (drop royal_ prefix, flatten)
    textures/block/particle.png                  (reused from base Royal models)

Also handles wall mirror renames:
    royal_wall_mirror_small (1x1 block) -> wall_mirror
    royal_wall_mirror_large (1x2 multi) -> wall_mirror_tall
"""
import shutil
from pathlib import Path

REF_MODELS = Path("../FantasyFurniture-1.19.4-ref/src/main/resources/assets/fantasyfurniture/models/block/decorations")
REF_TEX = Path("../FantasyFurniture-1.19.4-ref/src/main/resources/assets/fantasyfurniture/textures/block/models/decorations")
DST_MODELS = Path("src/main/resources/assets/fantasyfurniture_royal/models/block/decorations")
DST_TEX = Path("src/main/resources/assets/fantasyfurniture_royal/textures/block/decorations")

DST_MODELS.mkdir(parents=True, exist_ok=True)
DST_TEX.mkdir(parents=True, exist_ok=True)

# Wall mirror rename map: old_basename -> new_basename
MIRROR_RENAME = {
    "royal_wall_mirror_small": "wall_mirror",         # 1x1 single-block
    "royal_wall_mirror_large": "wall_mirror_tall",    # 1x2 multi-block
}

def rename_royal(name: str) -> str:
    """Convert 1.19.4 name (royal_X) to our name (X)."""
    if name in MIRROR_RENAME:
        return MIRROR_RENAME[name]
    if name.startswith("royal_"):
        return name[len("royal_"):]
    return name

def rewrite_model(text: str) -> str:
    """Rewrite texture paths in model JSON.

    fantasyfurniture:block/models/decorations/royal_X  ->  fantasyfurniture_royal:block/decorations/X
    fantasyfurniture:block/particles/royal             ->  fantasyfurniture_royal:block/particle
    """
    import re
    # decoration textures
    def decrepl(m):
        oldname = m.group(1)
        newname = rename_royal(oldname)
        # wall mirror tints keep the long suffix after rename
        if oldname.endswith("_tint"):
            base = oldname[:-len("_tint")]
            renamed = rename_royal(base) + "_tint"
            return f"fantasyfurniture_royal:block/decorations/{renamed}"
        return f"fantasyfurniture_royal:block/decorations/{newname}"

    text = re.sub(
        r"fantasyfurniture:block/models/decorations/([a-z0-9_]+)",
        decrepl,
        text,
    )
    text = text.replace(
        "fantasyfurniture:block/particles/royal",
        "fantasyfurniture_royal:block/particle",
    )
    return text

# --- models ---
model_count = 0
for f in sorted(REF_MODELS.glob("royal_*.json")):
    oldname = f.stem
    newname = rename_royal(oldname)
    text = f.read_text(encoding="utf-8")
    text = rewrite_model(text)
    (DST_MODELS / f"{newname}.json").write_text(text, encoding="utf-8")
    model_count += 1

# --- textures ---
tex_count = 0
for f in sorted(REF_TEX.glob("royal_*.png")):
    oldname = f.stem
    # Handle _tint suffix separately
    if oldname.endswith("_tint"):
        base = oldname[:-len("_tint")]
        newname = rename_royal(base) + "_tint"
    else:
        newname = rename_royal(oldname)
    dst = DST_TEX / f"{newname}.png"
    shutil.copy2(f, dst)
    tex_count += 1

print(f"Copied {model_count} Royal decoration model JSONs")
print(f"Copied {tex_count} Royal decoration textures")
print(f"Renamed: {list(MIRROR_RENAME.items())}")
