package com.github.impulsecl.impulse.core.extension;

import com.github.impulsecl.impulse.core.extension.context.DisableContext;
import com.github.impulsecl.impulse.core.extension.context.LoadingContext;

import edu.umd.cs.findbugs.annotations.NonNull;

public interface Extension {

  void load(@NonNull LoadingContext loadingContext);

  void disable(@NonNull DisableContext disableContext);

}
