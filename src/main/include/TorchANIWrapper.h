
#ifndef TORCHANI_WRAPPER_H_
#define TORCHANI_WRAPPER_H_

#ifndef TORCHANI_EXPORT
#define TORCHANI_EXPORT
#endif

#if defined(__cplusplus)
extern "C" {
#endif

extern TORCHANI_EXPORT double ANIEnergyAndGradient(const char* pathToTorch, long n, const int species[], const double coordinates[], double gradient[]);


#if defined(__cplusplus)
}
#endif

#endif /*TORCHANI_WRAPPER_H_*/
